//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program.  If not, see http://www.gnu.org/licenses/.
//

import inet.applications.netperfmeter.NetPerfMeterHost;
import inet.networklayer.configurator.ipv4.FlatNetworkConfigurator;
import inet.networklayer.configurator.ipv4.IPv4NetworkConfigurator;
import inet.node.inet.Router;
import inet.node.inet.StandardHost;

import ned.DatarateChannel;


@license(LGPL);

network QuicTest
{
    types:
        channel drc extends DatarateChannel
        {
            delay = 0.01ms;
            datarate = 12Mbps;
        }

    submodules:
        router1: Router {
            gates:
                pppg[2];
        }
        router2: Router {
            gates:
                pppg[2];
        }
        host1: NetPerfMeterHost {
            gates:
                pppg[1];
        }
        host2: NetPerfMeterHost {
            gates:
                pppg[1];
        }
    connections:
        host1.pppg[0] <--> drc <--> router1.pppg[0];
        router1.pppg[1] <--> drc <--> router2.pppg[0];
        router2.pppg[1] <--> drc <--> host2.pppg[0];
}
